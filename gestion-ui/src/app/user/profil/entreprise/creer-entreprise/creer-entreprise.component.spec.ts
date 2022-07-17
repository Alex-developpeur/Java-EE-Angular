import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerEntrepriseComponent } from './creer-entreprise.component';

describe('CreerEntrepriseComponent', () => {
  let component: CreerEntrepriseComponent;
  let fixture: ComponentFixture<CreerEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerEntrepriseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
