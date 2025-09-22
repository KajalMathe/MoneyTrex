import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-trans-update',
  templateUrl: './trans-update.component.html',
  styleUrls: ['./trans-update.component.css']
})
export class TransUpdateComponent implements OnInit {
  public tid:any=undefined;
  public trans:any=undefined;
  public msg:String=""
  constructor(private http:HttpClient,private userSer:UserService,
    private activeroute:ActivatedRoute,private router:Router){
    this.tid=activeroute.snapshot.paramMap.get("id");
  }

  ngOnInit(): void {
      const token=this.userSer.getUserInfo().token;
      const headers=new HttpHeaders({'Authorization':"Bearer " +token});
      this.http.get("http://localhost:8080/auth/cust/gettrans/"+this.tid,{headers}).subscribe(
        (result:any)=>{
          if(result.status)
            this.trans=result.data;
        }
      )
  }

public updTrans(){
  const token=this.userSer.getUserInfo().token;
  const headers=new HttpHeaders({'Content-Type':'application/json','Authorization':"Bearer " +token});
  this.http.put("http://localhost:8080/auth/cust/updtrans/"+this.tid,this.trans,{headers}).subscribe(
    (result:any)=>{
      if(result.status)
          this.router.navigateByUrl("/customer/trans")
      this.msg=result.msg;

    }
  )
}


}
