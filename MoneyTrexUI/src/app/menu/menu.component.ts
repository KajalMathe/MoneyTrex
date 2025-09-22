import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  constructor(public userSer:UserService,private router:Router){}

  public logout(){
    this.userSer.setUserInfo({islogin:false,token:undefined});
    this.router.navigateByUrl("/");
  }

}
