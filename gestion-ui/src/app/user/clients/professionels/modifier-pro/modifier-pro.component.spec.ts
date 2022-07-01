import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierProComponent } from './modifier-pro.component';

describe('ModifierProComponent', () => {
  let component: ModifierProComponent;
  let fixture: ComponentFixture<ModifierProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
