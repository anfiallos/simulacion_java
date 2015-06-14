trace <- read.table("c:/r/ejecucion3.dat")
names(trace) = c("tiempo","evento")
trace
newdata <- subset(trace, evento=="machine_failure",select=tiempo:evento) 
newdata







