curl -i "http://localhost:8080/api/v1/customers?sortBy=DATE"

curl -i --header "Content-Type: application/json" \
       --request POST \
       --data '{"username":"radoslaw","password":"majdan"}' \
       http://localhost:8080/login

curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdXBhIiwiaWF0IjoxNjA4MzMyNjY5LCJleHAiOjE2MDg0MTkwNjl9.LkOT2hiJ3iEMe91-hamDtf3Ug72JualjdRApih4YiYq1HN8xfOZhh-hiFkXET63bznM3Fw_euppC94HLrIW8Ow" http://localhost:8080/api/v1/customers?sortBy=DATE

curl -i --cookie "JSESSIONID=918D5C98D6200A4B19A347D4580F1581" "http://localhost:8080/api/v1/customers?sortBy=DATE"