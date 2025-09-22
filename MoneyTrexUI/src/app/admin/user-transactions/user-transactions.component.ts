import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-user-transactions',
  templateUrl: './user-transactions.component.html',
  styleUrls: ['./user-transactions.component.css']
})
export class UserTransactionsComponent {
  public translist:any=[]
  public msg:string=""
  public uid:any={};
  public unm:any;
  
  constructor(private http:HttpClient,private userServ:UserService,private actrt:ActivatedRoute){
    this.uid=actrt.snapshot.paramMap.get("id");
    this.unm=actrt.snapshot.paramMap.get("nm");
    console.log(this.uid+"---"+this.unm)
  }
   
  
    ngOnInit(): void {

        const token=this.userServ.getUserInfo().token;
        const headers=new HttpHeaders({'Authorization':"Bearer "+token});
        this.http.get("http://localhost:8080/auth/admin/translist/"+this.uid,{headers}).subscribe((result:any)=>{
          if(result.status)
            this.translist=result.data;
          this.msg=result.msg;
        });
    }
    
public generatePDF() {
    const table = document.getElementById('mytable');
    

    if (table) {
      html2canvas(table).then(canvas => {
        const imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF('p', 'mm', 'a4');

        const imgWidth = 208;
        const pageHeight = 295;
        const imgHeight = canvas.height * imgWidth / canvas.width;
        let heightLeft = imgHeight;

        let position = 0;

        pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;

        // Add more pages if needed
        while (heightLeft >= 0) {
          position = heightLeft - imgHeight;
          pdf.addPage();
          pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
          heightLeft -= pageHeight;
        }

        pdf.save('user-transaction.pdf');
      });
    } else {
      console.error("Table element not found!");
    }
  }


}
