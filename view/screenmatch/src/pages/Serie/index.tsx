import { useParams } from 'react-router-dom';
import Resume from '../../components/Resume';
import Return from '../../components/Return';
import { useChangeHeader, useGetEpisodesAtSeason, useGetJustOneSerie } from '../../state/hooks';
import { useRecoilValue } from 'recoil';
import { justOneSerie } from '../../state/atom';


export default function Serie() {
  const param = useParams();
  const oneSerie = useRecoilValue(justOneSerie);
  const changeHeader = useChangeHeader();
  const getJustOneSerie = useGetJustOneSerie();
  const getEpisodesAtSeason = useGetEpisodesAtSeason();

  changeHeader();
  getJustOneSerie(param.id);
  getEpisodesAtSeason(param.id);
  
  return (
    <>
      <Return />
      { oneSerie !== null ? <Resume { ...oneSerie }  /> : '' }
    </>
  );
}