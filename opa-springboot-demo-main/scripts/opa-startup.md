https://github.com/open-policy-agent/opa/blob/main/LICENSE

from: https://jcompetence.se/2023/06/22/open-policy-agent-opa-with-spring-boot-3/

start docker:
docker run -p 8181:8181 openpolicyagent/opa run --server --log-level debug

test: http://localhost:8080/v1/users
Bobby/password

Put:
http://localhost:8181/v1/policies/jcompetence.authz

package jcompetence.authz

import future.keywords
import future.keywords.every

default allow := false # By default deny allow access

#Allow access to the GET /v1/users API endpoint.
#Only if the user has the role (READ), or (ALL)
#Validate in order to GET, you must have read OR all permission
allow if {
    input.method == "GET"
    input.path = ["v1", "users"]
    allowed_roles := ["read", "all"]
    roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
    count(roles) > 0
}

#Allow access to the POST /v1/users API endpoint
#Only allow if user has the role (ALL)
#Validate in order to POST, you must have all permission
allow if {
    input.method == "POST"
    input.path = ["v1", "users"]
    roles := {role | some role in input.user.authorities; role.authority = "all"}
    count(roles) == 1
}

#Allow access to the PUT /v1/users API endpoint
#Only allow if user has the role (WRITE) or (ALL)
#A user should only be able to UPDATE its only data, otherwise DENY.
#Validate in order to PUT, you must have write permission + You are only updating your data
allow if {
    input.method == "PUT"
    input.path = ["v1", "users"]
    allowed_roles := ["write", "all"]
    roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
    upper(input.payload.firstname) = upper(input.user.username)
    count(roles) > 0
}

In our case, jcompetence.authz policies will be accessible through 
http://localhost:8181/v1/data/jcompetence/authz

Test the openpolicyagent/opa via restful Endpoint:
Post http://localhost:8181/v1/data/jcompetence/authz
With Json payload:
{
    "input": {
        "user": {
            "username": "patrick",
            "authorities": [{"authority": "all"}]
        },
        "path": ["v1", "users"],
        "method": "GET",
        "payload": {
            "firstname": "patrick"
        }
    }
}

it will return {"result": {"allow": true}}

----------------------------------------------------------------------------------------
<pre>
curl --location --request PUT 'http://localhost:8181/v1/policies/jcompetence.authz' \
--header 'Content-Type: text/plain' \
--data 'package jcompetence.authz
 
import future.keywords
import future.keywords.every
 
default allow := false
 
#Validate in order to GET, you must have read OR all permission
allow if {
    input.method == "GET"
    input.path = ["v1", "users"]
    allowed_roles := ["read", "all"]
    roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
    count(roles) > 0
}
 
#Validate in order to POST, you must have all permission
allow if {
    input.method == "POST"
    input.path = ["v1", "users"]
    roles := {role | some role in input.user.authorities; role.authority = "all"}
    count(roles) == 1
}
 
#Validate in order to PUT, you must have write permission + You are only updating your data
allow if {
    input.method == "PUT"
    input.path = ["v1", "users"]
    allowed_roles := ["write", "all"]
    roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
    upper(input.payload.firstname) = upper(input.user.username)
    count(roles) > 0
}
'
</pre>

-----------------------------------------------------------------------------------------

You can run OPA as a side container along with your microservice.
Create a Configmap named example-app-opa-policy with the rego policies, 
then in your DeploymentConfig mount the Configmap as a volume to the openpolicyagent sidecar image.

package jcompetence.authz

import future.keywords
import future.keywords.every

default allow := false

#Validate in order to GET, you must have read OR all permission
allow if {
input.method == "GET"
input.path = ["v1", "users"]
allowed_roles := ["read", "all"]
roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
count(roles) > 0
}

#Validate in order to POST, you must have all permission
allow if {
input.method == "POST"
input.path = ["v1", "users"]
roles := {role | some role in input.user.authorities; role.authority = "all"}
count(roles) == 1
}

#Validate in order to PUT, you must have write permission + You are only updating your data
allow if {
input.method == "PUT"
input.path = ["v1", "users"]
allowed_roles := ["write", "all"]
roles := {role | some role in input.user.authorities; role.authority = allowed_roles[_]}
upper(input.payload.firstname) = upper(input.user.username)
count(roles) > 0
}


----------
<pre>
volumes:
    - configMap:
        name: 'example-app-opa-policy'
      name: example-app-opa-policy
</pre>

----------
<pre>
volumeMounts:
  - name: example-app-opa-policy
    mountPath: /policies
terminationMessagePolicy: File
image: 'openpolicyagent/opa:edge-rootless'
args:
  - run
  - '--ignore=.*'
  - '--server'
  - '--log-level=debug'
  - '--log-format=json-pretty'
  - '--set=decision_logs.console=true'
  - /policies
</pre>






