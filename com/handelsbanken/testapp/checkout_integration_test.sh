set -euf

# Start application in the background.
"$1" 1>/dev/null &

# Wait for application to be ready
until curl localhost:8080/actuator/health -s 1>/dev/null; do
  sleep 1
done

function test_checkout() {
  result=$(curl localhost:8080/checkout -s -d "$1" -H 'Content-type:application/json')
  if ! (echo "${result}" | grep -F "$2") then
    echo -e "Test with input '$1' failed.\nGot '${result}' when expecting '$2'"
    exit 1
  fi
}

test_checkout '[10, 13]' '{"price":32.4}'
