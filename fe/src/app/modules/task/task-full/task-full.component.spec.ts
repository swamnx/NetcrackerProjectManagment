import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskFullComponent } from './task-full.component';

describe('TaskFullComponent', () => {
  let component: TaskFullComponent;
  let fixture: ComponentFixture<TaskFullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskFullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
