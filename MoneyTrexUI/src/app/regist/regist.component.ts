import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-regist',
  templateUrl: './regist.component.html',
  styleUrls: ['./regist.component.css']
})
export class RegistComponent {

  public message:String="";

  constructor(private http:HttpClient){}
  
  public register(nm:String,email:String,pass:String){
    this.http.post("http://localhost:8080/money/save",{name:nm,email:email,password:pass}).subscribe((result:any)=>{
      this.message=result.msg;
    });
  }

}
