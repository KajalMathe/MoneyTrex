import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService implements CanActivate{

  constructor(private userSer : UserService, private router: Router) {}


  canActivate(): boolean {
      if(this.userSer.getUserInfo().islogin){
        if(this.userSer.getUserInfo().role="ROLE_ADMIN"){
          this.router.navigateByUrl("/admin")
          return true
        }
        else{
          this.router.navigateByUrl("/customer")
          return false;
        }
     }
      else{
        this.router.navigateByUrl("/login")
        return false;
      }
  }
}
