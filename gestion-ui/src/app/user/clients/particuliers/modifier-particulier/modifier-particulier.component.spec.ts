import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierParticulierComponent } from './modifier-particulier.component';

describe('ModifierParticulierComponent', () => {
  let component: ModifierParticulierComponent;
  let fixture: ComponentFixture<ModifierParticulierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierParticulierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierParticulierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
