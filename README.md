# springboot-oracle-demo

# Requisitos
- Oracle 10G
- [Maven](https://maven.apache.org/download.cgi)
- [jdk8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

# Instalación 

1. Clonar o descargar este repositorio.
2. Descargar el Oracle Database 12.1.0.1 JDBC Driver  `ojdbc7` desde [Oracle](https://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html?source=post_page---------------------------)
3. Copiar en cualquier directorio e instalar con Maven: 
```
mvn install:install-file -Dfile={Path/to/your/ojdbc7.jar} -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
```


# Base de Datos

Para este ejemplo se utiliza la tabla `USUARIO` usando Oracle 10g.


```

	-- ----------------------------
	-- Table structure for USUARIO
	-- ----------------------------
	DROP TABLE "SYSTEM"."USUARIO";
	CREATE TABLE "SYSTEM"."USUARIO" (
	  "RUT" VARCHAR2(10 BYTE) NOT NULL ,
	  "DV" VARCHAR2(1 BYTE) ,
	  "NOMBRE" VARCHAR2(255 BYTE) ,
	  "APELLIDO" VARCHAR2(255 BYTE) 
	)
	TABLESPACE "SYSTEM"
	LOGGING
	NOCOMPRESS
	PCTFREE 10
	INITRANS 1
	STORAGE (
	  INITIAL 65536 
	  MINEXTENTS 1
	  MAXEXTENTS 2147483645
	  FREELISTS 1
	  FREELIST GROUPS 1
	  BUFFER_POOL DEFAULT
	)
	PARALLEL 1
	NOCACHE
	DISABLE ROW MOVEMENT
	;
	
	-- ----------------------------
	-- Primary Key structure for table USUARIO
	-- ----------------------------
	ALTER TABLE "SYSTEM"."USUARIO" ADD CONSTRAINT "SYS_C004027" PRIMARY KEY ("RUT");


```

# Compilar

Para compilar usar el comando : 

`mvn clean install`


