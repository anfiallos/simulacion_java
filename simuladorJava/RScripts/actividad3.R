trace <- read.table("c:/r/ejecucion3.dat")
names(trace) = c("tiempo","evento")
trace
newdata <- subset(trace, evento=="starting_repair" ,select=tiempo:evento) 
newdata
total1 = nrow(newdata[newdata$evento=="starting_repair",])
res1 = sum(newdata$tiempo)
newdata1 <- subset(trace, evento=="finishing_repair" ,select=tiempo:evento) 
total2 =nrow(newdata[newdata1$evento=="finishing_repair",])
newdata1
res2= sum(newdata1$tiempo)
reparacion =(res2-res1)/total1
cat("El promedio de reparacion es: ", reparacion)