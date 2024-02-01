FROM ubuntu:latest
LABEL authors="barte"

ENTRYPOINT ["top", "-b"]