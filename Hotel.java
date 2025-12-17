package hotel;

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
                 System.out.println(name+ " выселен из номера "+rooms[i].name+ " вместе со своей семьей "+family );
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

