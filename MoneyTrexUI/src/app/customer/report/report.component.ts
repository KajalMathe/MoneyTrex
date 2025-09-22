import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent {
public msg:String=""
public translist:any=null;
public months:String[]=[];
public details:any=null;

constructor(private http:HttpClient,private userSer:UserService){
  this.months=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
}

public showTrans(myform:any){
  this.details={...myform.value,month:this.months[myform.value.month-1]}
  const token=this.userSer.getUserInfo().token;
  const headers=new HttpHeaders({'Content-Type':"application/json",'Authorization':"Bearer "+token})
  this.http.post("http://localhost:8080/auth/cust/searchtrans",myform.value,{headers}).subscribe((result:any)=>{
    if(result.status)
      this.translist=result.data
  })
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

        pdf.save('transactions.pdf');
      });
    } else {
      console.error("Table element not found!");
    }
  }
}
