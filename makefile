RM := rm -fr

BUILDDIR  := simuladorJava/build
SRCDIR    := simuladorJava
ETCDIR    := etc
JAVA_BUILDDIR  := $(BUILDDIR)
PROJECT_JAR_NAME := simulacionDriven.jar
MANIFEST  := $(SRCDIR)/MANIFEST.MF
CARPETA_PRUEBA:=test
ARCHIVO_ENTRADA_DRIVEN:= $(BUILDDIR)/datos.txt


MAKE_BUILD_WARNING := echo "Something is wrong, try 'make compile' first"
MAKE_ALL_WARNING   := echo "Something is wrong, try 'make all' first"


compile: 	
	javac -classpath $(classpath) -sourcepath $(SRCDIR)/*.java -d $(JAVA_BUILDDIR)
	jar cfm $(PROJECT_JAR_NAME) $(MANIFEST) -C $(JAVA_BUILDDIR) . 
	echo "compilacion exitosa. Jar " $(PROJECT_JAR_NAME) " generado." 	
clean:	
	$(RM) "$(BUILDDIR)" "$(PROJECT_JAR_NAME)" 

ejecutarTestDriven:
	java -cp $(PROJECT_JAR_NAME) DiscreteEventSimulation trace $(ARCHIVO_ENTRADA_DRIVEN)

