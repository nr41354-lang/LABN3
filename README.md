## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2502`

#### Выполнила: `Ремизова Анастасия Александровна`

#### Вариант: `18`

### Cодержание:
- [Постановка задачи](#1-постановка-задачи)
- [Выбор структуры данных](#2-выбор-структуры-данных)
- [Описание классов](#3-описание-классов)
- [Программа](#4-программа)
- [Анализ правильности решения](#6-анализ-правильности-решения)

### 1. Постановка задачи

> Необходимо написать программу для управления номерами в гостиннице по заданным правилам, включая возможность размещение гостей с животными в четных номерах.

### 2. Выбор структуры данных

|      | название переменной | Тип (в Java) | 
|------|---------------------|--------------|
| name | `name`              | `String`     |
|family| `family`            | `String`     | 
| pet  | `pet`               | `boolean`    | 
| count| `count`             | `int`        | 
|  i   | `i`                 | `int`        |
| j    | `j`                 | `int`        |
|number| `number             | `int`        |

## 3. Описание классов
class Person
Хранит информацию о человеке т.е состоит ли в семье, есть ли животное.
Конструкторы 
public Person(String name) конструктор для простого человека 
public Person(String name, boolean pet) конструктор для человека с животным 

class Hotel 
Осуществляет заселение и выселение людей, семей и групп как с животными, так и без согласно правилам размещения.
Используемые методы: 
svobRooms() - метод позволяет узнать какие номера свободни, а какие заняты и кем.
check(String name, int number) - метод, позволяющий заселить человека в заданный номер, если он свободен.
checkFirst(String name) - метод, позволяющий заселить человека в первый свободный номер.
checkGroup(String[] names) - метод, позволяющий заселить группу в свободные номераю
checkGroupFr(String[] names, int room) - метод, позволяющий заселить группу, начиная с определенного номера,если мест недостаточно заселяются только те, кому хватило мест.
out(int number) - метод, позволяющий выселить человека из заданного номера.
checkFamily(String[] names) - метод, позвляющий заселить семью в последовательные номера.
outFamily(String name) - метод, позволяющий выселить человека и его семью, если он состит в ней.
checkPet(String name, int number) - метод, позволяющий заселить человека с животными в четныее номера, если такие свободны.
checkGroupPet(String[] names) - метод, позволяющий заселить группу с животными в свободные четные номера.
checkFamilyPet(String[] names) - метод, позволяющий заселить семью с животными в последовательные четные номера, если таковы имеются.

class Test
класс позволяющий тестировать правильность работы программы, демонстрируя работу методов.

### 4. Программа
```java
package hotel;

public class Person {
    public String name;
    private boolean pet;
    public String family;
    public Person(String name){
        this.name=name;
        this.pet=false;
        this.family=null;
    }
    public Person(String name, boolean pet){
        this.name=name;
        this.pet=pet;
        this.family=null;
    }
}

public class Hotel {
 Person[] rooms;
 public Hotel(int n){
     rooms =new Person[n];
 }
 public void svobRooms(){
     System.out.println("Свободые номера на данный момент:");
     for(int i=0; i<rooms.length; i++) {
         if (rooms[i] == null) {
             System.out.print((i + 1) + " ");
         }
     }
         System.out.println();
         System.out.println("Занятые номера:");
         for (int i = 0; i < rooms.length; i++) {
             if (rooms[i] != null)
                 System.out.println((i + 1) + " - " + rooms[i].name);
         }
     }
 public boolean check(String name, int number){
   if(number> rooms.length)
       return false;
   if(rooms[number-1]==null){
       rooms[number-1]=new Person(name);
       System.out.println( name+" заселен в номер "+ number);
       return true;
   }
     System.out.println("Номер "+number+" занят");
   return  false;
 }
 public boolean checkFirst(String name){
     for(int i=0; i< rooms.length; i++){
         if(rooms[i]==null){
             rooms[i]= new Person(name);
             System.out.println(name +" заселен  в первый свободный номер "+(i+1));
             return true;
         }
     }
     System.out.println("Нет свободных номеров");
     return false;
 }
 public int checkGroup(String[] names){
     int count=0;
     for(int i=0; i< names.length; i++){
     if(checkFirst(names[i])){
     count++;
     }
     }
     System.out.println("Группа аселена в "+ count);
     return count;
 }
 public int checkGroupFr(String[] names, int room){
     int count=0;
     for(int i=room-1; i< rooms.length && count< names.length; i++){
         if(rooms[i]==null){
             rooms[i]= new Person(names[count]);
             count++;
         }
     }
     System.out.println("Группа  заселена с начиная номера "+ room+ " в эти номера "+ count);
     return count;
 }
 public boolean out(int number){
     if(number <1 ||number> rooms.length){
         return false;}
     if (rooms[number-1]!= null);
     System.out.println(rooms[number-1].name+ " выселен из номера "+ number);
     rooms[number-1]= null;
     return true;
 }
 public boolean checkFamily(String[] names){
     for(int i=0; i<=rooms.length- names.length; i++){
         boolean fre = true;
     for(int j=0; j< names.length; j++) {
         if (rooms[i + j] != null)
             fre = false;
     }

     if(fre) {
         String family = "family" + i;
     for(int j=0; j< names.length; j++){
         rooms[i+j]= new Person(names[j]);
         rooms[i+j].family = family;
     }
         System.out.println("Семья заселена с номера "+ (i+1));
     return true;
     }
     }
     System.out.println("Нет места для семьи");
     return false;
 }
 public void outFamily(String name){
     String family = null;
     for (int i = 0; i < rooms.length; i++) {
         if (rooms[i] != null && rooms[i].name.equals(name)) {
             family = rooms[i].family;
             break;
         }
     }

     for (int i = 0; i < rooms.length; i++) {
         if (rooms[i] != null) {
             if (family != null && family.equals(rooms[i].family)) {
                 System.out.println(name+ " выселен из номера вместе со своей семьей );
                 rooms[i] = null;
             } else if (family == null && rooms[i].name.equals(name)) {
                 System.out.println(name+ " выселен из номера "+rooms[i].name);
                 rooms[i] = null;
             }
         }
     }
 }
  public boolean checkPet(String name, int number){
     if(number%2!=0 && number>name.length()){
         System.out.println("Нельзя заселиться с животными");
         return false;}

         if(rooms[number-1]==null){
         rooms[number-1]= new Person(name);
         System.out.println(name+ " заселен с животным в номер "+ number);
         return true;
     }
      System.out.println("Номер "+number+" занят");
     return false;
  }
  public int checkGroupPet(String[] names){
     int count=0;
     for(int i = 0; i<rooms.length && count< names.length; i++){
         if(rooms[i]==null && (i+1)%2==0){
             rooms[i]= new Person(names[count],true);
             count++;
         }
     }
      System.out.println("Группа с животными заселена в номера "+ count);
     return count;
  }
  public boolean checkFamilyPet(String[] names){
     for(int i=0; i< rooms.length- names.length; i++){
         boolean fre=true;
         for(int j=0; j< names.length; j++){
             if(rooms[i+j]!=null || (i+j+1)%2!=0){
                 fre=false;
             }
         }
         if(fre){
             String family = "family"+i;
             for(int j=0; j<names.length; j++){
                 rooms[i+j]= new Person(names[j], true);
                 rooms[i+j].family=family;
             }
             System.out.println("Семья с животными заселена в номера "+ (i+1));
             return true;
         }
     }
      System.out.println("Нет места для семьи с животными");
     return false;
  }
}

public class Test {
    public static void main(String[] args){
        // создаем гостинницу
        Hotel hot = new Hotel(15);
        // заселяем человека в номер
        hot.check("Ann", 1);
        hot.svobRooms();
        System.out.println();
        // заселяем в первый свободный номер
        hot.checkFirst("Vlad");
        hot.svobRooms();
        System.out.println();
        // заселение группы людей

        String[] group = {"Alice", "Jon"};
        hot.checkGroup(group);
        hot.svobRooms();
        System.out.println();
        // заселение группы, начиная с заданного номера

        String[] group2 ={"Goga", "Max", "Liza"};
        hot.checkGroupFr(group2, 7);
        hot.svobRooms();
        System.out.println();
        // выселение человека

        hot.out(1);
        hot.svobRooms();
        System.out.println();
        // заселение семьи

        String[] family= {"Lev","Igor", "Egor"};
        hot.checkFamily(family);
        hot.svobRooms();
        System.out.println();
        // выселение человека с семьей

        hot.outFamily("Egor");
        hot.svobRooms();
        System.out.println();
        // заселение человека с животным

        hot.checkPet("Oleg", 2);
        hot.svobRooms();
        System.out.println();
        // заселение группы с животным
        String[]  grouppet={"Lika", "Alla", "Alena"};
        hot.checkGroupPet(grouppet);
        hot.svobRooms();
        System.out.println();
        // заселение семьи с животным
        String[] familypet={"Inessa", "Katya", "Danya"};
        hot.checkFamilyPet(familypet);
        hot.svobRooms();
        System.out.println();
    }
}
```

### 5. Анализ правильности решения
тестирующий класс показывает работу каждого метода. после кождого применения нового метода программа выводит список свободных на данный момент номеров и список занятых номеров и имена кем они заняты.
1. Тест:
   Input:
  ```
15
Ann, 1  Vlad  Alise Jon    Goga Max Liza    1    Lev Igor Egor   Egor     Oleg,2    Lika Alla Alena      Inessa Katya Danya
```

 Output:
```
Ann заселен в номер 1
Свободые номера на данный момент:
2 3 4 5 6 7 8 9 10 11 12 13 14 15 
Занятые номера:
1 - Ann

Vlad заселен  в первый свободный номер 2
Свободые номера на данный момент:
3 4 5 6 7 8 9 10 11 12 13 14 15 
Занятые номера:
1 - Ann
2 - Vlad

Alice заселен  в первый свободный номер 3
Jon заселен  в первый свободный номер 4
Группа аселена в 2
Свободые номера на данный момент:
5 6 7 8 9 10 11 12 13 14 15 
Занятые номера:
1 - Ann
2 - Vlad
3 - Alice
4 - Jon

Группа  заселена с начиная номера 7 в эти номера 3
Свободые номера на данный момент:
5 6 10 11 12 13 14 15 
Занятые номера:
1 - Ann
2 - Vlad
3 - Alice
4 - Jon
7 - Goga
8 - Max
9 - Liza

Ann выселен из номера 1
Свободые номера на данный момент:
1 5 6 10 11 12 13 14 15 
Занятые номера:
2 - Vlad
3 - Alice
4 - Jon
7 - Goga
8 - Max
9 - Liza

Семья заселена с номера 10
Свободые номера на данный момент:
1 5 6 13 14 15 
Занятые номера:
2 - Vlad
3 - Alice
4 - Jon
7 - Goga
8 - Max
9 - Liza
10 - Lev
11 - Igor
12 - Egor

Egor выселен из номера вместе со своей семьей 
Свободые номера на данный момент:
1 5 6 10 11 12 13 14 15 
Занятые номера:
2 - Vlad
3 - Alice
4 - Jon
7 - Goga
8 - Max
9 - Liza

Номер 2 занят

Группа с животными заселена в номера 3
Свободые номера на данный момент:
1 5 11 13 14 15 
Занятые номера:
2 - Vlad
3 - Alice
4 - Jon
6 - Lika
7 - Goga
8 - Max
9 - Liza
10 - Alla
12 - Alena

Нет места для семьи с животными
Свободые номера на данный момент:
1 5 11 13 14 15 
Занятые номера:
2 - Vlad
3 - Alice
4 - Jon
6 - Lika
7 - Goga
8 - Max
9 - Liza
10 - Alla
12 - Alena
```
2. тест
   Input:
  ```
15
Ann, 5   Vlad     Alise Jon German    Goga Max Liza Sergey  3   Lev Igor Egor   Lev     Oleg,4    Lika Alla Alena Vika     Inessa Katya Danya
```

- Output:
```
Ann заселен в номер 5
Свободые номера на данный момент:
1 2 3 4 6 7 8 9 10 11 12 13 14 15 
Занятые номера:
5 - Ann

Vlad заселен  в первый свободный номер 1
Свободые номера на данный момент:
2 3 4 6 7 8 9 10 11 12 13 14 15 
Занятые номера:
1 - Vlad
5 - Ann

Alice заселен  в первый свободный номер 2
Jon заселен  в первый свободный номер 3
German заселен  в первый свободный номер 4
Группа аселена в 3
Свободые номера на данный момент:
6 7 8 9 10 11 12 13 14 15 
Занятые номера:
1 - Vlad
2 - Alice
3 - Jon
4 - German
5 - Ann

Группа  заселена с начиная номера 7 в эти номера 4
Свободые номера на данный момент:
6 11 12 13 14 15 
Занятые номера:
1 - Vlad
2 - Alice
3 - Jon
4 - German
5 - Ann
7 - Goga
8 - Max
9 - Liza
10 - Sergey

Vlad выселен из номера 1
Свободые номера на данный момент:
1 6 11 12 13 14 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
7 - Goga
8 - Max
9 - Liza
10 - Sergey

Семья заселена с номера 11
Свободые номера на данный момент:
1 6 14 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
7 - Goga
8 - Max
9 - Liza
10 - Sergey
11 - Lev
12 - Igor
13 - Egor

Lev выселен из номера  вместе со своей семьей 
Свободые номера на данный момент:
1 6 11 12 13 14 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
7 - Goga
8 - Max
9 - Liza
10 - Sergey

Номер 4 занят
Свободые номера на данный момент:
1 6 11 12 13 14 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
7 - Goga
8 - Max
9 - Liza
10 - Sergey

Группа с животными заселена в номера 3
Свободые номера на данный момент:
1 11 13 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
6 - Lika
7 - Goga
8 - Max
9 - Liza
10 - Sergey
12 - Alla
14 - Alena

Нет места для семьи с животными
Свободые номера на данный момент:
1 11 13 15 
Занятые номера:
2 - Alice
3 - Jon
4 - German
5 - Ann
6 - Lika
7 - Goga
8 - Max
9 - Liza
10 - Sergey
12 - Alla
14 - Alena
```
