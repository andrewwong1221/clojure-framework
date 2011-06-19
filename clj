#!/bin/bash
# Runs Clojure using the classpath specified in the `.clojure` file of the
# current directory.
#
# Mark Reid <http://mark.reid.name>
# with changed by Andrew D. Wong <http://andrewdwong.com>
# CREATED: 2009-03-29
# LAST MODIFIED: 2011-06-19

# Add extra jars as specified by `.clojure` file
JAVA="java"
CLJ_DIR=$HOME/local/clojure
CLOJURE=$CLJ_DIR/clojure.jar
CONTRIB=$CLJ_DIR/clojure-contrib.jar
JLINE=$CLJ_DIR/jline.jar
CP=$PWD:$CLOJURE:$JLINE:$CONTRIB

if [ -f .clojure ]
then
	CP=$CP: cat .clojure
fi

if [ -z "$1" ]; then
	$JAVA -server -cp $CP \
		jline.ConsoleRunner clojure.main
else
	$JAVA -server -cp $CP clojure.main -i $* 
fi
