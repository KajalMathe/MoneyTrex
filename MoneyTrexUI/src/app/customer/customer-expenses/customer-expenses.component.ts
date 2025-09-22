import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-customer-expenses',
  templateUrl: './customer-expenses.component.html',
  styleUrls: ['./customer-expenses.component.css']
})
export class CustomerExpensesComponent implements OnInit{

public limit:any=null;

constructor(private http:HttpClient,private userSer:UserService){}

ngOnInit(): void {
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({'Authorization':"Bearer "+token})
    this.http.get("http://localhost:8080/auth/cust/getlimit",{headers}).subscribe((result:any)=>{
      if(result.status)
        this.limit=result.data;
    })
}

public addLimit(myform:any){
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({'Content-Type':'application/json','Authorization':"Bearer "+token})
    this.http.post("http://localhost:8080/auth/cust/savelimit",myform.value,{headers}).subscribe((result:any)=>{
      if(result.status)
        this.limit=result.data;
    });

}

public del(){
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({'Authorization':"Bearer "+token})
    this.http.delete("http://localhost:8080/auth/cust/dellimit/"+this.limit.limiterid,{headers}).subscribe((result:any)=>{
      if(result.status)
        this.limit=null;
    });
}


}
