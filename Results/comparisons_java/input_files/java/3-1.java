import java.util.*;
import java.io.*;


public class Truck extends Vehicle {
    public Truck(String s1,String s2,String s3,double d1,double d2,double d3,String s4){
        super( s1, s2,s3,d1,d2,d3,s4);
    }
    public void result(){
        if(co2_level()<=25 && co_level()<=0.8 && hc_level()<=1000){
            setStatus("PASS");
        }
        else{
            setStatus("FAIL");
        }
        if(co2_level()==-1.0){
            setStatus("PENDING");
        }
    }
}

public class Car extends Vehicle {
    public Car(String s1,String s2,String s3,double d1,double d2,double d3,String s4){
        super( s1, s2,s3,d1,d2,d3,s4);
        
    }

    public void result(){
        if(co2_level()<=15 && co_level()<=0.5 && hc_level()<=750){
            setStatus("PASS");
        }
        else{
            setStatus("FAIL");
        }
        if(co2_level()==-1.0){
            setStatus("PENDING");
        }        
    }    
}

public class PollutionCheck{
    public static void main(String[] args) throws FileNotFoundException {
        
        String registered_vechicle="";
        String manufacture_name="";
        String owner="";
        
       
        
        

        List<Car> C=new ArrayList<>();
        List<Truck> T=new ArrayList<>();
        int itr_car=0,itr_truck=0;
        
        Scanner read1 = new Scanner(new File(args[0])).useDelimiter(",|\\n"); 
        
        int cnt=0;
        while(read1.hasNext()){
            String temp=read1.next();
            if(cnt%4==0) registered_vechicle=temp;
            if(cnt%4==1) manufacture_name=temp;
            if(cnt%4==2) owner=temp;
            if(cnt%4==3){
                
                if(temp.length()==4) {
                    C.add(new Car(registered_vechicle, manufacture_name, owner, -1.0, -1.0, -1.0,"PENDING"));
                    itr_car=itr_car+1;
                }
                if(temp.length()==6){
                    T.add(new Truck(registered_vechicle, manufacture_name, owner, -1.0, -1.0, -1.0,"PENDING"));
                   
                    itr_truck=itr_truck+1;
                }
            }
            
            cnt=cnt+1; 
        }
        
        read1.close();
       
        Scanner read2 = new Scanner(new File(args[1])).useDelimiter(",|\\n");
        
        String regis_pol="";
        double CO2=0.0;    
       double CO=0.0;
        double HC=0.0;
        int cnt1=0;
    
        int cnt_lines=0;
        while(read2.hasNext()){
            String temp=read2.next();
            cnt_lines=cnt_lines+1;
        }
        read2.close();
        
        Scanner read3 = new Scanner(new FileReader(args[1])).useDelimiter(",|\\n");
        
      
        
        for(int i=0;i<cnt_lines;i++){
            String temp1=read3.next();
            
            if(cnt1%4==0){ 
                regis_pol=temp1.substring(0, 6);
            }
            if(cnt1%4==1){
                String temp2=temp1.substring(1);
                CO2=Double.parseDouble(temp2);
                
                
                
            }
            if(cnt1%4==2){ 
                String temp2=temp1.substring(1);
                CO=Double.parseDouble(temp2);
                
            }
            if(cnt1%4==3){ 
                String temp2=temp1.substring(1);
                HC=Double.parseDouble(temp2);
                
                for(Car x : C){
                    if(x.getReg().equals(regis_pol)){
                        x.update(CO2,CO,HC);
                        x.result();
                        
                    }
                }
                for(Truck x : T){
                    if(x.getReg().equals(regis_pol)){
                        x.update(CO2,CO,HC);
                        x.result();
                        
                    }
                }
            }
            cnt1=cnt1+1;
        }
        
        read3.close();
               
        Scanner read4=new Scanner(new File(args[2])); 
        
        while(read4.hasNext()){
            String temp = read4.next();
            String temp1=temp.substring(0, 6);
            int i=0;
            int j=0;
            
            for(Car x : C){
                if(x.getReg().equals(temp1)){
                    i=-1;
                    x.checkPollutionStatus();
                }
            }
            for(Truck x: T){
                if(x.getReg().equals(temp1)){
                    j=-1;
                    x.checkPollutionStatus();
                }
            }
            if(i!=-1 && j!=-1){
                System.out.println("NOT REGISTERED");
            }
        }
        read4.close();
        
    }
}
