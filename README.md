This is a very simple demonstration application done for job interview.

Spring Boot was chosen for serving HTTP requests, mainly because it's a popular framework
that seemed to support what I needed. By using it I could learn about the framework and thus
kill two birds with one stone.

Spring Boot is only used for serving requests. While the framework also provides support
for providing database objects, I did not look into this other than note that there were
features that at least on the surface looks like they would fit.

# Building and running

## Prerequisites

First make sure that the correct version (7.4.0) of Bazel is available. If it is not, the recommended way is to use [Bazelisk](https://github.com/bazelbuild/bazelisk). It will take care of downloading and running the correct version of Bazel for you.

## Build

To build and run all tests:

```sh
bazel test ...
```

## Run application

To start the application, run

```sh
bazel run //com/handelsbanken/testapp:bankapp_boot
```

This will start a HTTP server listening to port 8080.

# Desired improvements

- Proper database backend. I decided this was out of scope since the task only calls for a single
  /checkout endpoint. Ideally the application itself should operate against a real database, and
  the integration test should be populating this database, either directly or through other api
  endpoints.
- Error checking on inputs. Considering this is meant as a shopping backend, it is important to
  verify that the input given won't cause any issues. As is, a malicous actor could potentially
  check out a huge number of items and overflow the price calculation.
