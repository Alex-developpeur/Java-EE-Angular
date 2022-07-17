import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauProfilComponent } from './nouveau-profil.component';

describe('NouveauProfilComponent', () => {
  let component: NouveauProfilComponent;
  let fixture: ComponentFixture<NouveauProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NouveauProfilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NouveauProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
