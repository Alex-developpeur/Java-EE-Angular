import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerParticulierComponent } from './creer-particulier.component';

describe('CreerParticulierComponent', () => {
  let component: CreerParticulierComponent;
  let fixture: ComponentFixture<CreerParticulierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerParticulierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerParticulierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
