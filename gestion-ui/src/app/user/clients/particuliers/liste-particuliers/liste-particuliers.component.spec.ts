import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeParticuliersComponent } from './liste-particuliers.component';

describe('ListeParticuliersComponent', () => {
  let component: ListeParticuliersComponent;
  let fixture: ComponentFixture<ListeParticuliersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeParticuliersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeParticuliersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
