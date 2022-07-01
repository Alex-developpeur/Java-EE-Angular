import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierProfilAdminComponent } from './modifier-profil-admin.component';

describe('ModifierProfilAdminComponent', () => {
  let component: ModifierProfilAdminComponent;
  let fixture: ComponentFixture<ModifierProfilAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierProfilAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierProfilAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
