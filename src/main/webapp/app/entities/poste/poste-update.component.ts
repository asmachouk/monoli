import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPoste, Poste } from 'app/shared/model/poste.model';
import { PosteService } from './poste.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe/employe.service';

@Component({
  selector: 'jhi-poste-update',
  templateUrl: './poste-update.component.html',
})
export class PosteUpdateComponent implements OnInit {
  isSaving = false;
  employes: IEmploye[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    departement: [null, [Validators.required]],
    salaire: [null, [Validators.required]],
    employeId: [],
  });

  constructor(
    protected posteService: PosteService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ poste }) => {
      this.updateForm(poste);

      this.employeService.query().subscribe((res: HttpResponse<IEmploye[]>) => (this.employes = res.body || []));
    });
  }

  updateForm(poste: IPoste): void {
    this.editForm.patchValue({
      id: poste.id,
      nom: poste.nom,
      departement: poste.departement,
      salaire: poste.salaire,
      employeId: poste.employeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const poste = this.createFromForm();
    if (poste.id !== undefined) {
      this.subscribeToSaveResponse(this.posteService.update(poste));
    } else {
      this.subscribeToSaveResponse(this.posteService.create(poste));
    }
  }

  private createFromForm(): IPoste {
    return {
      ...new Poste(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      departement: this.editForm.get(['departement'])!.value,
      salaire: this.editForm.get(['salaire'])!.value,
      employeId: this.editForm.get(['employeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPoste>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IEmploye): any {
    return item.id;
  }
}
