#!/bin/bash
# Runs Clojure using the classpath specified in the `.clojure` file of the
# current directory.
#
# Mark Reid <http://mark.reid.name>
# CREATED: 2009-03-29

JAVA=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home/bin/java 
CLJ_DIR=$(dirname $0)
CLOJURE=$CLJ_DIR/clojure.jar
JLINE=$CLJ_DIR/jline.jar
CP=$CLOJURE:$JLINE

# Add extra jars as specified by `.clojure` file
if [ -f .clojure ]
then
	CP=$CP:`cat .clojure`
fi

if [ -z "$1" ]; then 
	$JAVA -server -cp $CP \
	    jline.ConsoleRunner clojure.lang.Repl    
else
	scriptname=$1
	$JAVA -server -cp $CP clojure.lang.Script $scriptname -- $*
fi