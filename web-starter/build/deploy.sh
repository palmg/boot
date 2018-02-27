#!/bin/bash
echo "current path:"$(pwd)
cd ..
echo "begin maven deploy"
mvn clean deploy -U -Dmaven.test.skip=true
echo "end maven deploy"
