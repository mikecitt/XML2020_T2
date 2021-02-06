# Poverenik - Gradjanin - Sluzbenik
### Tim 2
##### Članovi:
- Aleksandar Nikolić SW27/2017
- Vladimir Rodušek SW23/2017
- Petar Cerović SW26/2017
- Milan Pavlov SW35/2017

##### Tehnologije:
Backend je rađen u Java Spring Boot,  
Frontend je rađen u Angular-u.  
Za čuvanje XML baza korišćena je Exist baza,  
dok je za RDF grafove korišćena Jena Fuseki.  

#### Pokretanje XML baza

```sh
cd servers/poverenik/exist-distribution-5.2.0/bin
./launcher.bat
(konfigurisati port na 8088)
./startup.bat
```
```sh
cd servers/sluzbenik/exist-distribution-5.2.0/bin
./launcher.bat
(konfigurisati port na 8089)
./startup.bat
```
#### Pokretanje RDF baza
```sh
cd servers/poverenik/apache-jena-fuseki-3.17.0
mkdir datasets
fuseki-server --tdb2 --loc=datasets --port 3030 /PoverenikDataset
```
```sh
cd servers/sluzbenik/apache-jena-fuseki-3.17.0
mkdir datasets
fuseki-server --tdb2 --loc=datasets --port 3031 /SluzbenikDataset
```
#### Pokretanje client aplikacija
Pokretanje Poverenik client aplikacije
```sh
cd poverenik/client
npm update
ng serve --port 4200
(go to http://localhost:4200)
```
Pokretanje Sluzbenik client aplikacije
```sh
cd sluzbenik/client
npm update
ng serve --port 4201
(go to http://localhost:4201)
```
#### Pokretanje servera
Pokretanje server aplikacija se vrši kao pokretanje Java Spring Boot aplikacije u nekom od Java opkruženja. Npr. IDEA Intellij.  
Server poverenika nalazi se na putanji
```sh
cd poverenik/server
```
Server službenika nalazi se na putanji
```sh
cd sluzbenik/server
```
