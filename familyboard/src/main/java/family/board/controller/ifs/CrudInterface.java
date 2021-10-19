package family.board.controller.ifs;

import family.board.model.network.Header;

public interface CrudInterface<Req,Res> {
    Header<Res> create(Header<Req> request); //추후
    Header<Res> read(Integer id);
    Header<Res> update(Header<Req> request);
    Header delete(Integer id);
}
