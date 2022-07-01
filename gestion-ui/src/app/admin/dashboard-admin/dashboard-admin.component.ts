import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../core';
import { faHome } from '@fortawesome/free-solid-svg-icons';

export interface Element {
  nom: string;
  nombre: string;
}

@Component({
	selector: 'app-dashboard-admin',
	templateUrl: './dashboard-admin.component.html',
	styleUrls: ['./dashboard-admin.component.scss']
})
export class DashboardAdminComponent implements OnInit {

    displayedColumns: string[] = ['nom', 'nombre'];
    dataSource: MatTableDataSource<Element>;

    constructor(private adminService: AdminService) { }

    ngOnInit() {
        this.reloadData();
    }

    reloadData() {
        this.adminService.getResourceList("Dashboard").subscribe(
            res => {
                this.dataSource = new MatTableDataSource(res);
            }, err => { console.log(err); });
    }

}
