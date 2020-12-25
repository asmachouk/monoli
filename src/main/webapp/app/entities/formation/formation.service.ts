import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormation } from 'app/shared/model/formation.model';

type EntityResponseType = HttpResponse<IFormation>;
type EntityArrayResponseType = HttpResponse<IFormation[]>;

@Injectable({ providedIn: 'root' })
export class FormationService {
  public resourceUrl = SERVER_API_URL + 'api/formations';

  constructor(protected http: HttpClient) {}

  create(formation: IFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formation);
    return this.http
      .post<IFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(formation: IFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formation);
    return this.http
      .put<IFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFormation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(formation: IFormation): IFormation {
    const copy: IFormation = Object.assign({}, formation, {
      date: formation.date && formation.date.isValid() ? formation.date.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((formation: IFormation) => {
        formation.date = formation.date ? moment(formation.date) : undefined;
      });
    }
    return res;
  }
}
