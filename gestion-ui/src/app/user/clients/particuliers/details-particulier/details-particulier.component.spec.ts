import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsParticulierComponent } from './details-particulier.component';

describe('DetailsParticulierComponent', () => {
  let component: DetailsParticulierComponent;
  let fixture: ComponentFixture<DetailsParticulierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsParticulierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsParticulierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
