# POC-Multithreading
## 1.Wat is multithreading?
 Het is de mogelijkheid van een enkele CPU core om 2 of meerdere threads naast elkaar uit te voeren (tegelijkertijd/parallel). 


## 2. -Wanneer gebruik je meerdere threads?
   - Om een taak parallel uit te voeren (optimalisatie)
	Bijvoorbeeld: een for loop met 10.000 iteraties opdelen over 4 threads, elke thread voert dan 2.500 iteraties uit. 
	
   - Om een taak die langer duurt in de achtergrond te laten uit voeren zonder de huidige thread er op te laten wachten.
	Bijvoorbeeld: Je wilt een file van disk lezen, dit lezen duurt een tijd voor de harde schrijf. In de tussentijd moet de 
	calling thread wachten op het resultaat. Het is hierin dus slimmer om het laten lezen te doen in een workerthread
	zodat de huidige thread (calling thread) kan doorgaan en niet hoeft te wachten.
	

## 3.Wat zijn drie veel voorkomende problemen bij mutithreaded applications? Waardoor ontstaan ze? 
  
   1. Deadlocks, dit onstaat wanneer bijv. als je 2 schatkisten hebt, A en B.
	En om A te openen heb je de sleutel in B nodig en om B te openen heb je de 	sleutel in A nodig. Op deze manier kan je nooit een kist openen.
	Dit is ook met Threads. Als ThreadA lockA in beheer heeft en probeert lockB te beheren terwijl ThreadB lockB in beheer heeft
	en lockA probeert in beheer te krijgen. Op deze manier, worden hun eigen locks nooit geunlocked en wachten de threads oneindig op elkaar.
   
   2. Race conditions. Bij het schrijven van data vanuit meerdere threads heb je locks nodig. Als dit fout gaat kun je allerlei lastig te debuggen problemen ondervinden. 
  
   3. Het ontwerpen van je applicatie zodanig dat het gebruik maakt van meerdere cores is een complexe bezigheid.
	Bijv, je kunt meerdere systemen in hun eigen threads laten werken, of allerlei taken opsplitsen in jobs
	bijv het laden van een plaatje of uitrekenen van een iets kun je in aparte taken/jobs opdelen.
	Echter, de interactie tussen de taken wordt een stuk complexer omdat overal rekening gehouden dient te worden met access vanuit meerdere threads en ook de afhankelijkheid
	van taken onderling maakt dit process ingewikkeld. Bijv. TaakC kan alleen uitgevoerd worden als TaakA  en TaakB klaar zijn, maar TaakA en TaakB kunnen wel naast elkaar uitgevoerd worden want die zijn niet
	afhankelijk van elkaar.

## 4. Hoe wordt het onderdeel genoemd waar objecten in het geheugen worden geplaatst? 
    heap memory
## Hoe is dit verschillend in een multithreaded application?
   De heap memory is gedeeld over alle threads en hier is een locking mechanisme nodig.
   In een multithreaded applicatie, kunnen threads tegelijkertijd memory opvragen of teruggeven. Bij dit proces komt een aantal takente bekijken, zoals
   bijhouden wat het volgende nieuwe vrije blok geheugen is, wat bezet is, etc.
   Deze administratie moet atomisch afgehandeld worden anders zou het kunnen zijn dat bijv Thread A een blok geheugen opvraagt die Thread B ook krijgt toegewezen
   omdat de ptr die verwijst naar een vrij blok niet atomisch is geupdate.

## 5. Hoe wordt het onderdeel genoemd waar methoden worden uitgevoerd en primitive types in het geheugen worden geplaatst?
    de stack
## Hoe is dit verschillend in een multithreaded application?
   Elke thread heeft eigen stack memory, daardoor kan er zonder locking mechanisme data gealloceerd worden in de thread.

## 6. Wat is in dit kader een racing condition? Hoe zou je dit kunnen voorkomen?
   Een racing condition onstaat wanneer twee of meer threads toegang hebben tot gedeelde gegevens en ze deze tegelijkertijd proberen te wijzigen.
   Race condition kunnen voorkomen worden door een soort vergrendelingsmechanisme toe te passen op de gedeelde gegevens.
    
    Voorbeeld :
    Stel je voor dat je 10 threads hebt die het volgende uitvoeren :
       
    for ( int i = 0; i < 10000000; i++ )
    {
       x = x + 1; 
    }
    
    Om ervoor te zorgen dat elke thread de waarde van x verhoogt, ze moeten het volgende doen: 
    
    Retrieve the value of x
    Add 1 to this value
    Store this value to x
    
    Elke thread kan op elk moment bij elke stap in dit proces zijn.
    De waarde van x kan worden gewijzigd door een andere thread gedurende de tijd tussen x wordt gelezen en wanneer het wordt teruggeschreven
        
    We plaatsten een locking rond de gedeelde gegevens om ervoor te zorgen dat slechts één thread tegelijk toegang heeft tot x.
       
    for ( int i = 0; i < 10000000; i++ )
    {
       //lock x
       x = x + 1; 
       //unlock x
    }
    
   }
            
## Bronnen

https://rmdiscala.developpez.com/cours/LesChapitres.html/Java/Cours3/Chap3.3.htm

https://fr.wikipedia.org/wiki/Thread_(informatique)

http://tutorials.jenkov.com/java-concurrency/synchronized.html

https://stackoverflow.com/questions/34510/what-is-a-race-condition

https://www.ni.com/nl-nl/support/documentation/supplemental/07/differences-between-multithreading-and-multitasking-for-programm.html


