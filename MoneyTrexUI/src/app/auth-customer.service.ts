import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthCustomerService implements CanActivate {

  constructor(private userSer : UserService, private router: Router) {}


  canActivate(): boolean {
      if(this.userSer.getUserInfo().islogin){
        if(this.userSer.getUserInfo().role="ROLE_CUSTOMER"){
          this.router.navigateByUrl("/customer")
          return true
        }
        else{
          this.router.navigateByUrl("/admin")
          return false;
        }
     }
      else{
        this.router.navigateByUrl("/login")
        return false;
      }
  }
}
