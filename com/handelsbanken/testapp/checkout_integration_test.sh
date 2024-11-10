set -euf

# Start application in the background.
"$1" >/dev/null &

# Wait for application to be ready
until curl localhost:8080/actuator/health -s >/dev/null; do
  sleep 1
done

function test_checkout() {
  result=$(curl localhost:8080/checkout -s -d "$1" -H 'Content-type:application/json')

  # NOTE: This really should be checked with jq instead of grep, I just didn't want to
  #       add a dependency on a command line tool less likely to be installed.
  if ! (echo "${result}" | grep -F "$2") > /dev/null; then
    echo -e "Test with input '$1' failed.\nGot '${result}' when expecting '$2'"
    exit 1
  fi
}

# For reference, these are the hard coded item ids and prices:
#
# 001: 100 (3 for 200)
# 002:  80 (2 for 120)
# 003:  50
# 004:  30
test_checkout '["001","002","001","004","003"]' '{"price":360}'
test_checkout '["001","001","001","001"]' '{"price":300}'
test_checkout '["002","002","002","002"]' '{"price":240}'
