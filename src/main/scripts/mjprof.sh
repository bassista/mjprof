#!/bin/sh
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
java -cp "$DIR/mjprof-1.0.jar:$DIR/libs/*.jar:$DIR/plugins/*" com.performizeit.mjstack.MJStack $*