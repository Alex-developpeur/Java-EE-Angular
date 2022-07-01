import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs";
import { MatDialog } from '@angular/material/dialog';
import { AdminService } from '../../../core';
import { Groupe } from "../../../model/groupe";
import { ModalSupprimerComponent } from '../../modal-supprimer/modal-supprimer.component';

@Component({
	selector: 'app-details-groupe',
	templateUrl: './details-groupe.component.html',
	styleUrls: ['./details-groupe.component.scss']
})
export class DetailsGroupeComponent implements OnInit {

	id: number;
	groupe: Groupe;

    constructor(private route: ActivatedRoute,private router: Router,
        private adminService: AdminService, public dialog: MatDialog) { }

    ngOnInit() {
        this.groupe = new Groupe();

        this.id = this.route.snapshot.params['id'];

        this.adminService.getResource("Groupe", this.id)
        .subscribe(data => {
            console.log(data)
            this.groupe = data;
        }, error => console.log(error));
    }

    deleteGroupe(id: number) {
        this.adminService.deleteResource("Groupe", id)
        .subscribe(
            data => {
                this.listGroupe();
            },
            error => console.log(error));
    }

    updateGroupe(id: number){
        this.router.navigate(['admin/groupes/modifier', id]);
    }

    openDialog(id: number) {
        let dialogRef = this.dialog.open(ModalSupprimerComponent, {
            height: '200px',
            width: '500px',
        });

        dialogRef.componentInstance.title = 'groupe';
        dialogRef.componentInstance.content = 'ce groupe';

        dialogRef.afterClosed().subscribe(result => {
            if(result)
                this.deleteGroupe(id);
            console.log('result : ' + result)
        });
    }    

    listGroupe(){
        this.router.navigate(['admin/groupes']);
    }

}
