import { Component, Input, OnInit } from '@angular/core';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

@Component({
	selector: 'app-modal-supprimer',
	templateUrl: './modal-supprimer.component.html',
	styleUrls: ['./modal-supprimer.component.scss']
})
export class ModalSupprimerComponent implements OnInit {

	@Input() title = '';
	@Input() content = '';

	faCheck = faCheck;
	faTimes = faTimes;

	constructor() { }

	ngOnInit(): void {
	}

}
