  
# build.bat written for the MovingPoint project on WINDOWS
# change directory to the projects src:
cd src

# remove unwanted files and prepare the git proccess:
echo MovingPoint: Clean Project Files
del *-BestOfCode.java
del *.class

# building the classes with verbose
echo MovingPoint: Building new Class-Files
javac -verbose *.java

# run MovingPoint with verbose on classes
java -verbose:class MovingPoint
