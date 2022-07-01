import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierProfilUserComponent } from './modifier-profil-user.component';

describe('ModifierProfilUserComponent', () => {
  let component: ModifierProfilUserComponent;
  let fixture: ComponentFixture<ModifierProfilUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierProfilUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierProfilUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
