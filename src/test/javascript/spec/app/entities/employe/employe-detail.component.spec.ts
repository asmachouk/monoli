import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { EmployeDetailComponent } from 'app/entities/employe/employe-detail.component';
import { Employe } from 'app/shared/model/employe.model';

describe('Component Tests', () => {
  describe('Employe Management Detail Component', () => {
    let comp: EmployeDetailComponent;
    let fixture: ComponentFixture<EmployeDetailComponent>;
    const route = ({ data: of({ employe: new Employe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [EmployeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EmployeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmployeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load employe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.employe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
