import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private userinfo:any;
  constructor() { 
    const data=localStorage.getItem("customer")
    if(data==undefined || data==null){
      this.userinfo={
    islogin:false, token:undefined, name:undefined,role:undefined
      }
    }
    else{
      this.userinfo=JSON.parse(data);
    }
  }
  public setUserInfo(info:any){
    this.userinfo=info;
    localStorage.setItem("customer",JSON.stringify(info))
  }
  public getUserInfo(){
    return this.userinfo;
  }
}
