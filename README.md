# Type: Exercici Pràctic Amb Teoria (With Theory)
# Language: Catalan
# Programming Language: Java
# Tools: IntelliJ, git
# Contents: behavior parametrization, modular programming, abstract methods, functional interfaces 

Primera part de l'exercici pràctic 2 de la UF2.

**Aquesta part no és avaluable però és necessària fer-la per a poder fer la segona part, que sí és avaluable**

Aquesta part de la pràctica serveix com a escalfament introductori.

## PROGRAMACIÓ MODULAR - PARAMETRITZACIÓ DEL COMPORTAMENT

Per a entendre què és la parametrització del comportament en programació, primer hem d'introduir uns conceptes previs, i després desenvoluparem un exemple, **que haureu de fer vosaltres** en el vostre ordinador, pas a pas.

### Conceptes previs

#### Estat i comportament dels objectes

Totes les instàncies de les classes, que també s'anomenen _objectes_, tenen definit un **estat** i un **comportament**.

* L'estat d'un objecte ve determinat pels valors que tenen les seves propietats.
* El comportament d'un objecte ve determinat per la implementació que tenen els seus mètodes.

Les propietats i mètodes es defineixen a la classe, que com ja sabeu, és una plantilla que defineix un tipus de dada nou a partir del qual es podran crear totes les instàncies (objectes) que el nostre programa necessiti.

Exemple:

```
public class Person {
    //Les propietats següents defineixen l'estat
    String name;
    int age;
    //etc...
    
    //Els mètodes següents defineixen el comportament
    void eatAndDrink() { 
       //Implementació aquí
    }
    boolean sleep() {
       //Implementació aquí
    }
    void doExercise() {
       //Implementació aquí (córrer, nedar ...)
    }
    //etc...
}

public class App {

   public static void main(String [] args) {
   
      //Creem un nou objecte de tipus Person i el guardem a la variable p1
      Person p1 = new Person(); 
      
      //L'estat de p1 ve definit pels valors que li assignem a les propietats:
      p1.name = "Von";
      p1.age = 43;
      
      //El comportament de la persona p1 ve definit 
      //per la implementació dels seus mètodes.
      //Si doExercise() està programat perquè la persona corri,
      //el comportament serà diferent que
      //si doExercise() estigués programat perquè la persona nedi.
      p1.doExercise(); 
      
      //La persona p1 compleix anys, per tant cal modificar el valor de age:
      p1.age = 44;
      //Diem que l'estat de p1 ha canviat (la persona ara és més gran -d'edat-)
   
   }

}

```

#### El mètode `equals()`

La classe String de Java té un mètode anomenat _equals_ que té per signatura:

```
boolean	equals(Object anObject);
```

És un mètode heredat de la classe Object, que és la superclasse (o classe pare) de totes les classes en Java. Per tant, podem usar _equals()_ també a totes les classes de Java, no només a String.

El paràmetre de tipus _Object_ significa que s'hi pot passar qualsevol classe (recordeu el concepte de polimorfisme de la pràctica 1 d'aquesta UF2). **En el cas dels String, hi passarem String**.

_equals()_, usat a la classe String, retorna **true** si la cadena de text que li passem com a paràmetre és igual que la cadena de text des de la qual es crida el mètode, o **false** si són cadenes de text diferents. Exemples:

```
//Exemple 1
String text1 = "Hola";
System.out.println(text1.equals("Hola")); //Imprimeix true

//Exemple 2
String text2 = "M3";
System.out.println(text2.equals("M03")); //Imprimeix false

//Exemple 3
boolean sonIguals = "Programacio".equals("Programming");
System.out.println(sonIguals); //Imprimeix false

```

**Amb tipus de dades no primitius, com a norma general, és necessari usar el mètode** `equals()`. **En canvi, per a tipus de dades primitius, usarem** `==` .

Exemples:

```
String text1 = "M3";
String text2 = "M03";
if(text1 == text2) { ... } //No recomanable
if(text1.equals(text2)) { ... } //Recomanable

int n1 = 4, n2 = 8;
if(n1 == n2/2) { ... } //Correcte
if(n1.equals(n2/2)) { ... } //Error perquè n1 és primitiu
```


#### Mètodes abstractes

Un mètode abstracte és un mètode del qual no s'especifica cap implementació, és a dir, **només se'm mostra la seva signatura i el seu tipus de dada de retorn**.

Exemple de mètode abstracte:

```
int countGreenApples(Apple [] apples);
```

Si volem implementar aquest mètode, haurem de donar-li un cos. Per exemple:

```
int countGreenApples(Apple [] apples) {
   int amount = 0;
   for(Apple a : apples) {
      if(a.color.equals("green"))
         amount++;
   }
   return amount;
}
```

En aquest darrer exemple, el mètode _countGreenApples_ ha deixat de ser abstracte, perquè ara ja està implementat, és a dir, ja té un cos (bloc de codi) amb codi.


#### Interfícies funcionals

En programació existeixen també uns tipus de blocs de codi que s'anomenen **interfícies**. Hi ha diferents tipus d'interfícies, que es treballaran i es veuran amb detall a la UF4. 

De moment, a nosaltres ens interessen un tipus particular d'interfícies: les **interfícies funcionals**.

Les interfícies són molt semblants a les classes, amb una gran diferència: els mètodes que s'encapsulen dins de les interfícies són **mètodes abstractes** (amb una excepció, que es veurà a la UF4).

Les interfícies també poden tenir propietats, igual que les classes, però no és habitual usar propietats a les interfícies. 

Les **interfícies funcionals** són les interfíes que compleixen la següent condició:

* Només tenen 1 mètode (**que serà abstracte**)

Aquestes interfícies, les funcionals, són les que ens interessen i són les que usarem.

Exemple d'interfície funcional:

```
public interface ApplePredicate {
   boolean test(Apple apple);
}
```

En aquest exemple, el nom de la interfície és _ApplePredicate_ i com que només té un sol mètode (que és abstracte) és doncs una interfície funcional.

Només es mostra la signatura del mètode _test_ i el tipus del seu valor de retorn, que ha de ser un booleà.

El mètode _test_ és una funció que retorna un booleà, i ja sabem de la UF1, que les funcions que retornen un booleà s'anomenen **predicats**. Per aquest motiu hem decidit que el nom de la interfície sigui _ApplePredicate_, tot i que es podria anomenar de qualsevol altra manera.

**Un mètode abstracte no es pot cridar** doncs no està implementat, és a dir, no té codi.

Perquè un mètode abtracte es pugui cridar, **cal** primer **implementar-lo**. Però la implementació **no** es fa a la interfície, sinó que es fa a una classe, la qual direm que implementa la interfície. Exemple:

```
public interface ApplePredicate {
   boolean test(Apple apple);
}

public class AppleGreenPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.color.equals("green");
   }
}
```

Diem que `AppleGreenPredicate` és una classe que implementa la interfície funcional `ApplePredicate`. Però no és la única, doncs podem crear tantes implementacions com vulguem, per exemple:

```
public class AppleRedPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.color.equals("red");
   }
}
```

El codi que crida el mètode `test` ho farà així:

```
Apple a = new Apple();

ApplePredicate apg = new AppleGreenPredicate();
if(apg.test(a)) System.out.println("The color of the apple is green");

ApplePredicate apr = new AppleRedPredicate();
if(apr.test(a)) System.out.println("The color of the apple is res");

ApplePredicate ap = new ApplePredicate(); 
//Error, una interfície NO es pot instanciar
```


## Parametrització del comportament - Exemple

**Heu de fer amb IntelliJ IDEA les passes d'aquest exemple i anar comprovant que funcioni.**

_Descripció de l'exemple:_

> **`Requeriments inicials:`**

Volem fer una aplicació que, donada una col·lecció de pomes (apples), ens calculi quantes pomes són verdes (green).

#### Pas 1

Creem un nou projecte amb IDEA, que anomenarem m3-uf2-ep2-1.

Hi afegim les classes App i Apple que trobareu a la carpeta _green_ d'aquest enunciat.


#### Pas 2

Heu d'imprimir per pantalla el següent missatge:
```
The total number of green apples is: X
```
on X és el número total de pomes de color verd.

Aquest missatge s'ha d'imprimir des del mètode main() de la classe App. 

Ho heu de fer afegint el mètode següent a la classe App:

```
static int countGreenApples(Apple [] apples) {
  //Implementació aquí
}
```
A l'apartat **Conceptes previs - Mètodes abstractes** teniu una implementació possible d'aquest mètode.

> **`Canvi en els requeriments:`**

El nostre client ara també vol que la nostra aplicació ens calculi en número total de pomes vermelles (red). Per tant, els requeriments de la nostra aplicació han canviat. El canvi consisteix en què ara ens demanen que la nostra aplicació faci més coses (calcular el número de pomes vermelles, i també les verdes com ja feia).

#### Pas 3

Afegiu el codi necessari a la classe App per tal que l'aplicació ara calculi també el número de pomes vermelles i imprimeixi també el següent missatge:
```
The total number of red apples is: X
```
Una primera possible solució és la que podeu trobar a la carpeta _red_ d'aquest enunciat.

En aquesta solució es defineix una segona funció que servirà per a calcular el número de pomes vermelles. Si us fixeu, aquest mètode nou _countRedApples_ és idèntic al mètode que ja teníem _countGreenApples_ amb l'única diferència que canviem _green_ per _red_:

```
static int countRedApples(Apple [] apples) {
   int amount = 0;
   for(Apple a : apples) {
      if(a.color.equals("red"))
         amount++;
   }
   return amount;
}
```

Si comparem aquest nou mètode amb l'anterior:

```
static int countGreenApples(Apple [] apples) {
   int amount = 0;
   for(Apple a : apples) {
      if(a.color.equals("green"))
         amount++;
   }
   return amount;
}
```
veiem que el codi és pràcticament idèntic. 

#### Pas 4

Aquesta darrera solució ens pot semblar una solució acceptable, doncs el codi és petit i funciona. Però **què passaria si el nostre client canvia de nou els seus requeriments i ara ens demanar calcular el número de pomes grogues?** i si les pomes tinguessin 15 colors diferents? **hauríem de repetir el codi 15 vegades en 15 mètodes diferents quan l'única cosa que canvia és només el color, essent la resta de codi igual per a tots els mètodes?**

> Una altra possible solució seria la de modificar el mètode que calcula el número de pomes, fent-lo més genèric **parametritzant el color**, és a dir, indicar el color de la poma com a un segon paràmetre:

```
static int countApplesByColor(Apple [] apples, String color) {
   int amount = 0;
   for(Apple a : apples) {
      if(a.color.equals(color))
         amount++;
   }
   return amount;
}
//a.color és el color de la poma
//color és el color que passem com a paràmetre
```
Aquesta solució és, clarament, molt millor que l'anterior, doncs no hem de repetir el codi per a cada color. Teniu aquest codi a la carpeta _parametrizedColor_. Comproveu que el codi funcioni (l'heu de completar).

#### Pas 5

> **`Nou canvi en els requeriments:`**

El nostre client ara ens demana que vol que el programa calculi el número de pomes pesades, considerant que les pomes pesades són aquelles que pesen 150 grams o més.

Com programadors novells que som, se'ns acudeix d'afegir al nostre programa un altre mètode que s'encarregui de calcular quantes pomes pesen 150 grams o més:

```
static int countHeavyApples(Apple [] apples){
   int amount = 0;
   for(Apple a : apples) {
      if(a.weight >= 150)
         amount++;
   }
   return amount;
}
```

#### Pas 6

> **`Nou canvi en els requeriments:`**

Però el nostre client, de nou, ens torna a demanar que el nostre programa faci, encara, una cosa més: ha de calcular quantes pomes són lleugeres, és a dir, que pesin 115 grams o menys.  

Sincerament, comencem a estar una mica cansats de tants canvis com ens demana el nostre client, però com que és el client qui paga, no ens queda altra remei que tornar a modificar el nostre programa. Ara, però, amb l'experiència que ja tenim del càlcul de pomes segons el color, enlloc de proposar afegir una altra funció com la següent:

```
static int countLightApples(Apple [] apples){
   int amount = 0;
   for(Apple a : apples) {
      if(a.weight <= 115)
         amount++;
   }
   return amount;
}
```

directament pensem a fer una **parametrització del pes** de la poma:

```
static int countApplesByWeight(Apple [] apples, int weight){
   int amount = 0;
   for(Apple a : apples) {
      if(a.weight >= weight)
         amount++;
   }
   return amount;
}
```
Veiem que cridant aquesta funció de la següent manera:

```
int numberOfHeavyApples = countApplesByWeight(apples, 150);
```
podem obtenir el número total de pomes pesades.

Però si cridem:
```
int numberOfHeavyApples = countApplesByWeight(apples, 115);
```
**no** estem obtenint el número total de pomes lleugeres, ja que perquè així fos, el mètode hauria de ser:
```
static int countApplesByWeight(Apple [] apples, int weight){
   int amount = 0;
   for(Apple a : apples) {
      if(a.weight <= weight)
         amount++;
   }
   return amount;
}
```
Fixeu-vos que calia, també, canviar l'operador de comparació. Per al càcul del número de pomes pesades necessitem `>=` i per al càlcul de les pomes lleugeres necessitem `<=`.

La pregunta que ens ve al cap és: no hi ha alguna manera de **parametritzar** també l'operador de comparació, com hem fet amb el color o amb el pes? Podem fer alguna cosa com la següent?

```
static int countApplesByWeight(Apple [] apples, int weight, String operator){
   int amount = 0;
   for(Apple a : apples) {
      if(operator.equals(">=")) {
         if(a.weight >= weight)
            amount++;
      } else if(operator.equals("<=")) {
         if(a.weight <= weight)
            amount++;      
      }
   }
   return amount;   
}

//Cridem
int numberOfHeavyApples = countApplesByWeight(apples, 150, ">=");
int numberOfLightApples = countApplesByWeight(apples, 115, "<=");
```
Ja podem veure com el codi queda molt lleig, i més lleig quedaria si haguessim de considerar més operadors com `<`, `>` o `==` (havent d'escriure més `if`). És una solució difícil de mantenir i d'ampliar. Podeu comprovar que funciona (la teniu a la carpeta _parametrizedWeight_, -hi heu de completar el codi) però **no és recomanable**. Veurem que hi ha una altra solució, molt millor. Veiem primer, però, un altre exemple.


Continuem al següent pas i veiem un altre exemple:


#### Pas 7

> **`Nou canvi en els requeriments:`**

Quan creiem que ja podíem descansar, ve de nou el nostre client (sembla que ho faci expressament!!!) i ara ens demana que el nostre programa ha de calcular el número de pomes que són verdes **i**, al mateix temps, són pesades (pesen 150 gram o més).

Una primera solució, molt innocent, seria crear un mètode com el següent:

```
static int countGreenAndHeavyApples(Apple[] apples){
   int amount = 0;
   for(Apple a : apples) {
       if(a.color.equals("green") 
               && a.weight >= 150){
           amount++;
       }
   }
   return amount;       
}
```
Aquesta solució es pot trobar a la carpeta _greenAndHeavy_ d'aquest anunciat. Completeu el codi i comproveu que funciona.

#### Pas 8

Penseu que aquesta darrera solució és una bona solució?

Què passa si el client ens demana calcular quantes pomes hi ha que sigui verdes i lleugeres? i vermelles i pesades? i pomes pesades grogues o verdes? i vermelles i lleugeres o grogues i pesades ... ? Què hem de fer? Ens tornem bojos creant una infinitat de mètodes diferents, amb codis quasi bé iguals? O comencem a parametritzar les diferents propietats i condicions?

Si ens plantegem a parametritzar les diferents propietats i condicions dels operadors, podríem acabar amb un mètode amb una signatura com la següent:

```
int countApplesByProperties(Apple[] apples, 
                            String color1, 
                            String color2, 
                            int weight, 
                            String operator1, 
                            String operator2, 
                            boolean flag1, 
                            boolean flag2){
 ....
}
```
on els paràmetres booleans servirien per a indicar si només volem calcular segons el color, només segons el pes, o segons el color i el pes a la vegada.

Aquesta solució és **una bogeria**. No és escalable, no és mantenible, és propensa als errors, és difícil d'entendre, amb codi farragós i llarg, incapaç d'adaptar-se amb senzillesa i rapidesa als nous canvis dels requeriments, que el nostre estimat client sempre ens regala.

### Pas Final. Solució: Parametrització del comportament

Creem la interfície funcional `ApplePredicate` (que ja he vist a l'apartat **Conceptes previs - Interfícies funcionals**)

```
public interface ApplePredicate {
   boolean test(Apple apple);
}
```

El següent pas és **crear varies classes que implementin aquesta interfície, una per a cadascun dels requeriments del nostre client**:

> Requeriment: comptar quantes pomes verdes tenim
```
public class AppleGreenPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.color.equals("green");
   }
}
```

> Requeriment: comptar quantes pomes vermelles tenim

```
public class AppleRedPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.color.equals("red");
   }
}
```

> Requeriment: comptar quantes pomes pesades tenim
```
public class AppleHeavyPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.weight >= 150;
   }
}
```

> Requeriment: comptar quantes pomes lleugeres tenim
```
public class AppleLightPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.weight <= 115;
   }
}
```

> Requeriment: comptar quantes pomes verdes i pesades, a la vegada, tenim
```
public class AppleGreenAndHeavyPredicate implements ApplePredicate {
   boolean test(Apple apple) {
      return apple.color.equals("green") 
             && apple.weight >= 150;
   }
}
```

D'aquesta manera, per a fer els càlculs, no necessitem diferents funcions, només ens en cal **una**:

```
int countApples(Apple [] apples, ApplePredicate predicate) {
   int amount = 0;
   for(Apple a : apples) {
      if(predicate.test(a))
         amount++;
   }
   return amount;
}
```

I la manera que tenim ara per a poder fer tots els càlculs, cridant aquest mètode des de psvm, és:

```
Apple apples[] = init();

//Comptem el número de pomes verdes
int numberOfGreenApples = countApples(apples, new AppleGreenPredicate());

//Comptem el número de pomes vermelles
int numberOfRedApples = countApples(apples, new AppleRedPredicate());

//Comptem el número de pomes pesades
int numberOfHeavyApples = countApples(apples, new AppleHeavyPredicate());

//Comptem el número de pomes lleugeres
int numberOfLightApples = countApples(apples, new AppleLightPredicate());

//Comptem el número de pomes verdes i, també, pesades
int numberOfGreenAndHeavyApples = countApples(apples, new AppleGreenAndHeavyPredicate());
```

**Cada implementació de la interfície funcional representa un COMPORTAMENT diferent, en el sentit que s'avalua de forma diferent com es fa la selecció de les pomes. El que estem fent és PARAMETRITZAR aquest comportament, afegint un nou paràmetre de tipus `ApplePredicate` a la funció `countApples`.**

**A l'hora de cridar la funció `countApples`, el valor que podem donar al paràmetre `ApplePredicate` pot ser qualsevol objecte o instància de qualsevol de les classes que implementen la interfície `ApplePredicate`. Depenent de quina és la classe que instanciem i passem com a valor en aquest segon paràmetre, estarem modificant de quina manera es fa la selecció de les pomes, és a dir, estarem modificant el comportament del mètode `countApples`.**

Podeu trobar i testejar el codi final a la carpeta _parametrizedBehavior_, que heu de completar.

### En la part 2 d'aquesta pràctica veurem com encara podem simplificar més aquest codi usant les expressions lambda.
