cd .\src
javac @..\compile.txt dk\mtdm\Main.java
cd .\..\bin
cmd /c ""c:/program files/java/jdk-17/bin/java"" Main
cd .\..
echo "program finished"