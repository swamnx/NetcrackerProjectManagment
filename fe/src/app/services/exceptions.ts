export function recogniseError(status:string):string{
    if(status == '404') return 'Not found';
    if(status == '403') return 'Forbidden';
    if(status == '500') return 'Internal Server Error';
    if(status == '504') return 'Gateway Timeout';
    if(status == '409') return 'Conflict';
}