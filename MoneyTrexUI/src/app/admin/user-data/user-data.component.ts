import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit{
  public userlist:any[]=[]
  public msg:String="";
  constructor(private http:HttpClient,private userServ:UserService){}
  
  ngOnInit(): void {
      const token=this.userServ.getUserInfo().token;
      console.log(token)
      const headers=new HttpHeaders({'Authorization':"Bearer "+token});
      this.http.get("http://localhost:8080/auth/admin/userdata",{headers}).subscribe((result:any)=>{
        if(result.status)
          this.userlist=result.data;
        this.msg=result.msg;
      });
  }
  
  public changeStatus(uid:any)
  {
    console.log(this.userServ.getUserInfo())
      const token=this.userServ.getUserInfo().token;
      const headers=new HttpHeaders({'Content-Type':'application/json','Authorization':"Bearer "+token});
      this.http.put("http://localhost:8080/auth/admin/changestatus/"+uid,{},{headers}).subscribe((result:any)=>{
        console.log(result);
        if(result.status)
          this.userlist.filter((u:any)=>u.userid==uid?{...u,status:!u.status}:u.status)

      });

  }

}

