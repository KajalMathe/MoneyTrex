import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-customer-transaction',
  templateUrl: './customer-transaction.component.html',
  styleUrls: ['./customer-transaction.component.css']
})
export class CustomerTransactionComponent implements OnInit {
  private transList:any[] = [];
  public transGroupList:any = {};
  public expenseLimit:number = 0;
  public msg:String="";
  
  
  constructor(private http:HttpClient,private userSer:UserService){}

  ngOnInit(): void {
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({'Authorization':"Bearer "+token});
    
    this.http.get("http://localhost:8080/auth/cust/getlimit", { headers })
        .subscribe((result: any) => {
            if(result.status)
              this.expenseLimit = result.data.limits;
        });

  
    this.http.get("http://localhost:8080/auth/cust/translist",{headers}).subscribe((result:any)=>
      {
            this.transList = result.data;
            this.transGroupList = this.groupData();
      });
  }
  
    public getKeys():any{
    return Object.keys(this.transGroupList);
  }
  public getRecords(key:any):any
  {
    return this.transGroupList[key];
  }
  public getBalance(key:any):number
  {
    const trans = this.transGroupList[key];
    const exps = trans.reduce((pv:any,cv:any)=>cv.type=='expense'?pv+cv.amount:pv,0);
    return this.expenseLimit - exps;
  }

    public groupData()
  {
      const data:any = {};
      this.transList.forEach(t=>{
          const td = t.entrydate.split("-");
          const key = td[1]+"-"+td[0];

          if(Object.keys(data).includes(key)){
              data[key].push(t)
          }else{
              data[key] = [t];
          }
      });

      return data;
  }

  public makeTrans(myform:any){
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({'Content-Type':"application/json", 'Authorization':"Bearer "+token});
    this.http.post("http://localhost:8080/auth/cust/savetrans",myform.value,{headers}).subscribe((result:any)=>{
      if(result.status){
              this.transList.push(result.data);
              myform.reset();
               this.transGroupList = this.groupData()
            }
              else
              this.msg = "Transaction Save Failed !"
      });
      
  }

  public delTrans(tid:any){
    const token=this.userSer.getUserInfo().token;
    const headers=new HttpHeaders({ 'Authorization':"Bearer "+token});
    this.http.delete("http://localhost:8080/auth/cust/deltrans/"+tid,{headers}).subscribe(
      (result:any)=>{
        if(result.status){
              const i = this.transList.findIndex(tr=>tr.trans_id==tid);
              this.transList.splice(i,1);
              this.transGroupList = this.groupData()
            }
              else
              this.msg = result.msg;
        });


  }


}
