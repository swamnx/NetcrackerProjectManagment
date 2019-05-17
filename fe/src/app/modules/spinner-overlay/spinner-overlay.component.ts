import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material';
@Component({
  selector:'app-do-not-use',
  template:`<mat-spinner></mat-spinner>`,
  styleUrls:['./spinner-overlay.component.css']
})
export class DialogSpinnerComponent{}

@Component({
  selector: 'app-spinner-overlay',
  templateUrl: './spinner-overlay.component.html',
  styleUrls: ['./spinner-overlay.component.css']
})
export class SpinnerOverlayComponent implements OnInit {
  private dialog:MatDialogRef<DialogSpinnerComponent>
  constructor(private matDialog:MatDialog) { }

  ngOnInit() {
      setTimeout(()=>{
        this.dialog=this.matDialog.open(DialogSpinnerComponent,{panelClass:'spin',disableClose:true})
      })
  }
  ngOnDestroy(){
    setTimeout(()=>{
      this.dialog.close();
    })
  }

}
