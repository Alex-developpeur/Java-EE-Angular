import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeProComponent } from './liste-pro.component';

describe('ListeProComponent', () => {
  let component: ListeProComponent;
  let fixture: ComponentFixture<ListeProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
