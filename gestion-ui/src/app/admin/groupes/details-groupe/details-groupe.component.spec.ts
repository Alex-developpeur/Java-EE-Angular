import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsGroupeComponent } from './details-groupe.component';

describe('DetailsGroupeComponent', () => {
  let component: DetailsGroupeComponent;
  let fixture: ComponentFixture<DetailsGroupeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsGroupeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsGroupeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
